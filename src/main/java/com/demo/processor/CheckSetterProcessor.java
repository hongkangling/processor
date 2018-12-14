package com.demo.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-13 15:40
 **/
@SupportedAnnotationTypes(value = "com.demo.processor.CheckSetter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CheckSetterProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //  todo field 获取注解的属性集合
        for (TypeElement annotation : annotations) {
            if (!"com.demo.processor.CheckSetter".equals(annotation.getQualifiedName().toString())) {
                continue;
            }
            Set<TypeElement> rootElements =ElementFilter.typesIn(roundEnv.getRootElements());
            for (TypeElement element : rootElements) {
			
            Set<VariableElement> typeElements = ElementFilter.fieldsIn(roundEnv.getElementsAnnotatedWith(CheckSetter.class));
            for (VariableElement field : typeElements) {
                System.out.println("字段名："+field.getSimpleName().toString());
                if (!containsSetter(element,field.getSimpleName().toString())){
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            String.format("setter not found '%s.%s'.",element.getSimpleName(),field.getSimpleName()));
                }
            }
            }
        }
        return true;
    }

    private static boolean containsSetter(TypeElement typeElement,String  name){
        String setter="set"+name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        // 获取类或接口的方法集合
        System.out.println("typeElement"+typeElement.getEnclosedElements());
        for (ExecutableElement executableElement:ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
           // 判断该方法是否是静态方法 判断该方法是否等于setter 该方法参数不能为空
            System.out.println("方法："+executableElement.getSimpleName().toString());

            if(!executableElement.getModifiers().contains(Modifier.STATIC)
                    &&executableElement.getSimpleName().toString().equals(setter)
                    &&!executableElement.getParameters().isEmpty()){
                return true;
            }
        }
        return false;
    }































}
